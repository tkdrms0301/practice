import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
A = ((1, 2, 3), (4, 5, 6), (7, 8, 9))
B = ((9, 8, 7), (6, 5, 4), (3, 2, 1))

C = [[A[i][j] + B[i][j] for j in range(len(A[i]))] for i in
range(len(A))]
C = [tuple(l) for l in C] # 리스트 항목들을 튜플 항목으로
C = tuple(C); print(C)