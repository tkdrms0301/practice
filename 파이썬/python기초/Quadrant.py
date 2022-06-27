import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
strX = input("좌표의 x값 >> ")
x = int(strX)
strY = input("좌표의 y값 >> ")
y = int(strY)
if x == 0 or y == 0:
    print("x나 y 중 하나가 0이면 분면을 판단할 수 없습니다.")
elif x > 0:
    if y > 0:
        qud = 1
    else:
        qud = 4
    print("좌표 (" + str(x) + ", " + str(y) + ")의 분면은 " + str(qud) + "분면입니다.")
else :
    if y > 0:
        qud = 2
    else:
        qud = 3
    print("좌표 (" + str(x) + ", " + str(y) + ")의 분면은 " + str(qud) + "분면입니다.")