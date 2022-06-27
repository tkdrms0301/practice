import random
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
x = random.randint(0, 100) # 0 <= x <= 100 정수
if x % 2 == 0:
    quest = 'e'
else:
    quest = 'o'
guess = input("0과 100 사이의 난수가 하나 생성 되었음. 짝수라 생각하면 e를, 홀수라고 생각하면 o를 입력 : ")
if guess == quest:
    print("축하합니다!!!")
else:
    print("정답은 " + str(x) +"\n다음 기회에 ...")