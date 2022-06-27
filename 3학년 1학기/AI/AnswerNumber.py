import random
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
quest = random.randint(1, 100) # 1 <= quest <= 100 정수
cont = 'y'
while cont == 'y':
    guess = int(input("예측값 (1과 100 사이) ? "))
    if guess > quest:
        print("정답보다 큽니다.")
    elif guess < quest:
        print("정답보다 작습니다.")
    else:
        print("정답입니다!!!")
        cont = 'n'