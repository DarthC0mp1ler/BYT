#include "menuState.h"

MenuState::MenuState()
{
	counter = 0;
	stateStatus = (char*)"displaying";
}

MenuState::~MenuState() 
{
	delete[] stateStatus;
}

GameState* MenuState::Update()
{
	counter++;
	return 0;
}

void MenuState::Display() const 
{
	std::cout << stateStatus << " " << counter << std::endl;
}

void MenuState::ModifyStateStatus(char* newStatus)
{
	stateStatus = new char[strlen(newStatus) + 1];
	strcpy(stateStatus, newStatus);
}

char* MenuState::GetStateStatus() const
{
	return stateStatus;
}
