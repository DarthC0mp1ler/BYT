#include "AdditionCalculator.h"

AdditionCalculator::AdditionCalculator(Calculator* calc) 
{
	successor = calc;
}

AdditionCalculator::~AdditionCalculator()
{
	delete successor;
}

std::string AdditionCalculator::Calculate(double num1, double num2 ,char command)
{
	if (command == 'a' || command == 'A')
	{
		return std::to_string(num1) + " + " + std::to_string(num2) + " = " + std::to_string(num1 + num2) + "\n";
	}
	else if(successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}
std::string AdditionCalculator::Calculate(int num1, int num2,char command)
{
	if (command == 'a' || command == 'A')
	{
		return std::to_string(num1) + " + " + std::to_string(num2) + " = " + std::to_string(num1 + num2) + "\n";
	}
	else if (successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}