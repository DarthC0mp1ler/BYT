#include "SubtractionCalculator.h"

SubtractionCalculator::SubtractionCalculator(Calculator* calc)
{
	successor = calc;
}

SubtractionCalculator::~SubtractionCalculator()
{
	delete successor;
}

std::string SubtractionCalculator::Calculate(double num1, double num2, char command)
{
	if (command == 's' || command == 'S')
	{
		return std::to_string(num1) + " - " + std::to_string(num2) + " = " + std::to_string(num1 - num2) + "\n";
	}
	else if (successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}
std::string SubtractionCalculator::Calculate(int num1, int num2, char command)
{
	if (command == 's' || command == 's')
	{
		return std::to_string(num1) + " - " + std::to_string(num2) + " = " + std::to_string(num1 - num2) + "\n";
	}
	else if (successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}