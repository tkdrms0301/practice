import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
n = int(input("양의 정수 n의 값을 입력하시오. "))
if n == 1:
    print("소수가 아닙니다."); exit(0)
prime = 'y'
for i in range(2, n):
    if n % i == 0:
        prime = 'n'
        break
if prime == 'y':
    print("소수입니다.")
else:
    print("소수가 아닙니다.")