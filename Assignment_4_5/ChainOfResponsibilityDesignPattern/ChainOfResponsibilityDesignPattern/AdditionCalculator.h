#include "Calculator.h"
class AdditionCalculator : Calculator
{

public:
	AdditionCalculator(Calculator* calc);
	~AdditionCalculator();

	std::string Calculate(double, double, char);
	std::string Calculate(int, int, char);
};

