#include <iostream>
#include "Pool.h"

void print(std::vector<Connection*> vec)
{
	using namespace std;
	
	cout << "Printing used connections: " << endl;
	
	for(auto con: vec)
	{
		cout << "\t" << con->GetConnectionType() << " " << con->GetServerName() << " " << con->GetServerAddress() << endl;
	}
}


int main()
{
	using namespace std;

	ConnectionsPool* pool = ConnectionsPool::GetInstance();

	vector<Connection*> tmp;

	tmp.push_back(pool->GetAvailable());
	tmp.back()->SetConnectionType((char*)"ConnectionType1");
	tmp.back()->SetServerAddress((char*)"ServerAddress1");
	tmp.back()->SetServerName((char*)"ServerName1");

	tmp.push_back(pool->GetAvailable());
	tmp.back()->SetConnectionType((char*)"ConnectionType2");
	tmp.back()->SetServerAddress((char*)"ServerAddress2");
	tmp.back()->SetServerName((char*)"ServerName2");

	tmp.push_back(pool->GetAvailable());
	tmp.back()->SetConnectionType((char*)"ConnectionType3");
	tmp.back()->SetServerAddress((char*)"ServerAddress3");
	tmp.back()->SetServerName((char*)"ServerName3");

	print(tmp);

	pool->Free(tmp.back());
	tmp.pop_back();

	tmp.push_back(pool->GetAvailable());
	tmp.back()->SetConnectionType((char*)"ConnectionType4");
	tmp.back()->SetServerAddress((char*)"ServerAddress4");
	tmp.back()->SetServerName((char*)"ServerName4");
	
	print(tmp);

	pool->SetMaxSize(5);

	tmp.push_back(pool->GetAvailable());
	tmp.back()->SetConnectionType((char*)"ConnectionType5");
	tmp.back()->SetServerAddress((char*)"ServerAddress5");
	tmp.back()->SetServerName((char*)"ServerName5");

	tmp.push_back(pool->GetAvailable());
	tmp.back()->SetConnectionType((char*)"ConnectionType6");
	tmp.back()->SetServerAddress((char*)"ServerAddress6");
	tmp.back()->SetServerName((char*)"ServerName6");


	print(tmp);

	ConnectionsPool::Destroy();
}

