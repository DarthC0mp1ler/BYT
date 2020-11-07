#include "hd.h"

Calculator* Instantiate()
{
	Calculator* calc = (Calculator*)new AdditionCalculator
	(
		(Calculator*)new SubtractionCalculator
		(
		(Calculator*)new DivisionCalculator
		(
			(Calculator*)new MultiplicationCalculator(0)
		)
		)
	);
	return calc;
}


int main()
{
	Calculator* calc = Instantiate();

	int n1 = 2, n2 = 3;
	double d1 = 2.2, d2 = 3.2;

	using namespace std;
	cout << calc->Calculate(n1,n2,'m');
	cout << calc->Calculate(n1, n2, 'n');
	cout << calc->Calculate(d1, d2, 'D');

	

}