#include "Fraction.h"
int main()
{
    Fraction f1(2, 3), f2(2, 5), //분자에만 음수 사용
    f3 = f1.add(f2); // 2/3 + 2/5
    f3.print();  // 16/15 출력되어야
    f3 = f1.subtract(f2); // 2/3 - 2/5
    f3.print();  // 4/15 출력되어야
    f3 = f1.multiply(f2); // 2/3 * 2/5
    f3.print();  // 4/15 출력되어야
    f3 = f1.divide(f2); // 2/3 / 2/5
    f3.print();  // 5/3 출력되어야
    return 0;
}