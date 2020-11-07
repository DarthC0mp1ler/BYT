#include "introState.h"
#include "menuState.h"

IntroState::IntroState()
{
	
	counter = 0;
	stateStatus = (char*)"introduction";

}

IntroState::~IntroState()
{
	delete[] stateStatus;
}

GameState* IntroState::Update()
{
	counter++;
	if (counter >= 10) {
		return (GameState*)new MenuState();
	}
	return 0;

}

void IntroState::Display() const
{
	std::cout <<stateStatus<<" " << counter << std::endl;
}

void IntroState::ModifyStateStatus(char* newStatus)
{
	stateStatus = new char[strlen(newStatus) + 1];
	strcpy(stateStatus, newStatus);
}

char* IntroState::GetStateStatus() const
{
	return stateStatus;
}