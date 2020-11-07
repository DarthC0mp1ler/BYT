#include "StateManager.h"

class MenuState : GameState
{

public:
	MenuState();
	~MenuState();

	GameState* Update();
	void Display() const;
	void ModifyStateStatus(char*);
	char* GetStateStatus() const;
};

