import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
n = int(input("n의 값을 입력하시오. "))
factorial = 1
for index in range(1, n+1):
    factorial *= index
print("1 ~ " + str(n) + "까지의 곱은 " + str(factorial) + "입니다.")