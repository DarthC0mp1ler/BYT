#include "Pool.h"

ConnectionsPool* ConnectionsPool::instance = 0;

ConnectionsPool::ConnectionsPool()
{
	maxSize = 3;
	for (size_t i = 0; i < maxSize; i++)
	{
		availableConnections.push_back(new Connection((char*)'\0', (char*)'\0', (char*)'\0'));
	}
}

void ConnectionsPool::Clean(Connection& con)
{
	con.SetConnectionType(0);
	con.SetServerName(0);
	con.SetServerAddress(0);
}

void ConnectionsPool::Free(Connection* con)
{
	Clean(*con);
	availableConnections.push_back(usedConnections.back());
	usedConnections.pop_back();
}

Connection* ConnectionsPool::GetAvailable()
{
	if (availableConnections.size() != 0)
	{
		usedConnections.push_back(availableConnections.back());
		availableConnections.pop_back();
		return usedConnections.back();
	}
	else if (maxSize > (availableConnections.size() + usedConnections.size()))
	{
		for (size_t i = 0; i < (maxSize - (availableConnections.size() + usedConnections.size())); i++)
		{
			availableConnections.push_back(new Connection((char*)'\0', (char*)'\0', (char*)'\0'));
		}
		return GetAvailable();
	}
	else return 0;
}

void ConnectionsPool::SetMaxSize(int size)
{
	maxSize = size;
}