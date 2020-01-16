/* DROP TRIGGERS AND FUNCTIONS */
DROP TRIGGER quiz_user_setter;
DROP FUNCTION quizLv;
DROP TRIGGER tp;
DROP TRIGGER qc;
DROP FUNCTION userTp;
/* DROP TRIGGER gd; */

/* CREATE TRIGGERS AND FUNCTIONS  */

/* quiz_user 테이블에 새로운 유저가 등록될 때마다(회원가입 시) 초기값 설정 트리거 */
create or replace trigger quiz_user_setter
before
   INSERT on quiz_user
for each row
begin
   :NEW.user_quizcount := 0;
   :NEW.user_totalpoint := 0;
   :NEW.user_grade := 'BEGINNGER';
end;
/

/* quiz_number 받아서 quiz_level return해주는 function */
CREATE OR REPLACE FUNCTION quizLv(q_number number)
RETURN quiz.quiz_level%TYPE
IS
   q_level quiz.quiz_level%TYPE;
   
BEGIN
   select quiz_level
      into q_level
   from quiz where quiz_number = q_number;
   return q_level;
end;
/

/* 회원이 본인이 푼 문제를 solved_quiz에 등록하면 해당 문제애 해당하는 점수를 quiz_user.user_totalPoint에 더해준다  */
CREATE OR REPLACE TRIGGER tp
AFTER INSERT OR DELETE ON solved_quiz
FOR EACH ROW 
DECLARE
   u_number number(2);
   q_number number(4);
   q_level varchar2(15);
   v_point number(2);
BEGIN
   IF INSERTING THEN
      u_number := :NEW.user_number;
      q_level := quizLv(:NEW.quiz_number);
   ELSE
      u_number := :OLD.user_number;
      q_level := quizLv(:OLD.quiz_number);
   END IF;
      
   IF q_level = 'Lv.1' THEN
      v_point := 1;
   ELSIF q_level = 'Lv.2' THEN
      v_point := 3;
   ELSIF q_level = 'Lv.3' THEN
      v_point := 10;
   ELSIF q_level = 'Lv.4' THEN
      v_point := 25;
   ELSIF q_level = 'Lv.5' THEN
      v_point := 50;
   END IF;
   
   IF INSERTING THEN
      UPDATE quiz_user SET user_totalPoint = user_totalPoint + v_point WHERE user_number = u_number;
         IF userTp(u_number) BETWEEN 1 AND 9 THEN
            UPDATE quiz_user SET user_grade = 'IRON' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(4) THEN
            UPDATE quiz_user SET user_grade = 'BRONZE' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(3) THEN
            UPDATE quiz_user SET user_grade = 'SILVER' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(2) THEN
            UPDATE quiz_user SET user_grade = 'GOLD' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(1) THEN
            UPDATE quiz_user SET user_grade = 'PLATINUM' WHERE user_number = u_number;
         ELSIF userTp(u_number) >= userTp(1) THEN
            UPDATE quiz_user SET user_grade = 'DIAMOND' WHERE user_number = u_number;
         END IF;
   ELSE
      UPDATE quiz_user SET user_totalPoint = user_totalPoint - v_point WHERE user_number = u_number;
               IF userTp(u_number) BETWEEN 1 AND 9 THEN
            UPDATE quiz_user SET user_grade = 'IRON' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(4) THEN
            UPDATE quiz_user SET user_grade = 'BRONZE' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(3) THEN
            UPDATE quiz_user SET user_grade = 'SILVER' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(2) THEN
            UPDATE quiz_user SET user_grade = 'GOLD' WHERE user_number = u_number;
         ELSIF userTp(u_number) < userTp(1) THEN
            UPDATE quiz_user SET user_grade = 'PLATINUM' WHERE user_number = u_number;
         ELSIF userTp(u_number) > userTp(1) THEN
            UPDATE quiz_user SET user_grade = 'DIAMOND' WHERE user_number = u_number;
         END IF;
   END IF;
END;
/

/* quiz_user.user_quizCount에 푼 문제 수 변동을 반영해주는 trigger */
CREATE OR REPLACE TRIGGER qc
AFTER INSERT OR DELETE ON solved_quiz
FOR EACH ROW 
DECLARE
   u_number number(2);
BEGIN
   IF INSERTING THEN
      u_number := :NEW.user_number;
   ELSE
      u_number := :OLD.user_number;
   END IF;
   
   IF INSERTING THEN
      UPDATE quiz_user SET user_quizCount = user_quizCount + 1 WHERE user_number = u_number;
   ELSE
      UPDATE quiz_user SET user_quizCount = user_quizCount - 1 WHERE user_number = u_number;
   END IF;
END;
/

/* user_number값을 받아 해당 유저의 user_totalpoint를 return해주는 function */      
CREATE OR REPLACE FUNCTION userTp(u_number number)
RETURN quiz_user.user_totalpoint%TYPE
IS
   u_tp quiz_user.user_totalpoint%TYPE;
BEGIN
   SELECT user_totalpoint
      INTO u_tp
   FROM quiz_user WHERE user_number = u_number;
   RETURN u_tp;
END;
/

/* quiz_user.user_quizCount에 푼 문제 수 변동을 반영해주는 trigger */
/* TRIGGER tc에 병합됨
CREATE OR REPLACE TRIGGER gd
BEFORE UPDATE OF USER_TOTALPOINT ON QUIZ_USER
FOR EACH ROW 
FOLLOWS tp
DECLARE
   u_number number(2);
   u_tp number(4);
BEGIN
   u_number := :NEW.user_number;
   u_tp := :NEW.user_totalpoint;
   
   IF ((u_tp BETWEEN 1 AND 9) AND :OLD.user_number = u_number) THEN
      :NEW.user_grade := 'IRON';
   ELSIF ((u_tp < userTp(4)) AND :OLD.user_number = u_number) THEN
      :NEW.user_grade := 'BRONZE';
   ELSIF ((u_tp < userTp(3)) AND :OLD.user_number = u_number) THEN
      :NEW.user_grade := 'SILVER';
   ELSIF ((u_tp < userTp(2)) AND :OLD.user_number = u_number) THEN   
      :NEW.user_grade := 'GOLD';
   ELSIF ((u_tp < userTp(1)) AND :OLD.user_number = u_number) THEN   
      :NEW.user_grade := 'PLATINUM';
   ELSIF ((u_tp > userTp(1)) AND :OLD.user_number = u_number) THEN
      :NEW.user_grade := 'DIAMOND';
   END IF;
END;
/   
*/

/* quiz_user table test data */
insert into QUIZ_USER(USER_NUMBER,USER_ID,USER_PW,USER_NAME,USER_GRADE)  values (user_number.nextval,'yyy3691', '251023', '박현민', 'DIAMOND');
insert into QUIZ_USER(USER_NUMBER,USER_ID,USER_PW,USER_NAME,USER_GRADE)  values (user_number.nextval,'yyy4770', '251123', '김종성', 'PLATINUM');
insert into QUIZ_USER(USER_NUMBER,USER_ID,USER_PW,USER_NAME,USER_GRADE)  values (user_number.nextval,'yyy8745', '251563', '유은나', 'GOLD');
insert into QUIZ_USER(USER_NUMBER,USER_ID,USER_PW,USER_NAME,USER_GRADE)  values (user_number.nextval,'yyy2415', '241023', '조영욱', 'SILVER');
insert into QUIZ_USER(USER_NUMBER,USER_ID,USER_PW,USER_NAME)  values (user_number.nextval,'yyy2416', '241025', '김동성');

/* solved_quiz table test data */


commit;
