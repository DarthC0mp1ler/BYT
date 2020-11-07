#include "StateManager.h"
#include <iostream>
class IntroState : GameState
{

	

public:
	IntroState();
	~IntroState();

	GameState* Update();
	void Display() const;
	void ModifyStateStatus(char*);
	char* GetStateStatus() const;

};

