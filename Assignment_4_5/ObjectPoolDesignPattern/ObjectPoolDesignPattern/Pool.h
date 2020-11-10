#include <vector>
#include "Connection.h"

class ConnectionsPool
{
	static ConnectionsPool* instance;

	std::vector<Connection*> availableConnections;
	std::vector<Connection*> usedConnections;
	int maxSize;

	ConnectionsPool();


	void Clean(Connection&);
public:

	void Free(Connection*);
	Connection* GetAvailable();
	void SetMaxSize(int);


	static ConnectionsPool* GetInstance()
	{
		if (!instance)
		{
			instance = new ConnectionsPool();
		}

		return instance;
	}

	static void Destroy()
	{
		delete instance;
	}


};

