import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
A = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
B = [[9, 8, 7], [6, 5, 4], [3, 2, 1]]
C = [[0,0,0], [0,0,0], [0,0,0]]
# 방법 1
for r in range(0, len(A)) :
    for c in range(0, len(A[r])):
        C[r][c] = A[r][c] + B[r][c]
print(C)

# 방법 2
C = [[A[i][j] + B[i][j] for j in range(len(A[i]))] for i in range(len(A))]
print(C)