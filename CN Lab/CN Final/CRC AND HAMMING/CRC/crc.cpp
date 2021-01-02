#include"iostream"
using namespace std;
#define MAX 50
void division(int temp[],int cgen[],int dsize,int gsize)
{
	for(int i=0;i<dsize;i++)
	{
		int k=i,j=0;
		if(temp[k]>=cgen[j])
		for(int j=0,k=i;j<gsize;j++,k++)
		{
			if( (temp[k]==1 && cgen[j]==1)||(temp[k]==0 && cgen[j]==0))
			temp[k]=0;
			else
			temp[k]=1;
		}
	}
}
int main()
{
	int dataword[MAX],dsize,cgen[MAX],gsize;
	
	cout<<"Enter The size of dataword: ";
	cin>>dsize;
	cout<<"Enter Dataword(with spaces in between): ";
	for(int i=0;i<dsize;i++)
	cin>>dataword[i];
	
	cout<<"Enter The size of Generator: ";
	cin>>gsize;
	cout<<"Enter Generator(with spaces in between): ";
	for(int i=0;i<gsize;i++)
	cin>>cgen[i];
	
	cout<<"\n\t*****Sender Side*****\n";
	cout<<"Dataword:";
	for(int i=0;i<dsize;i++)
	cout<<dataword[i];
	cout<<"\tGenerator:";
	for(int i=0;i<gsize;i++)
	cout<<cgen[i];
	
	int s=gsize-1;
	cout<<"\nNumber of 0's to be appended: "<<s<<endl;
	
	int temp[MAX];
	for(int i=0;i<dsize;i++)
	temp[i]=dataword[i];
	for(int i=dsize;i<dsize+s;i++)
	temp[i]=0;
	cout<<"Dataword after appending 0's: ";
	for(int i=0;i<dsize+s;i++)
	cout<<temp[i];
	cout<<endl;
	
	division(temp,cgen,dsize,gsize);
	
	int crc[MAX];
	for(int i=0,j=dsize;i<s;i++,j++)
	crc[i]=temp[j];
	cout<<"\nCRC bits: ";
	for(int i=0;i<s;i++)
	cout<<crc[i];
	
	int trans_data[MAX];
	cout<<"\nData to be Transmitted: ";
	for(int i=0;i<dsize;i++)
	trans_data[i]=dataword[i];
	for(int i=dsize,j=0;j<s;i++,j++)
	trans_data[i]=crc[j];
	for(int i=0;i<dsize+s;i++)
	cout<<trans_data[i];
	
	//trans_data[4]=1;
	cout<<"\n\n\t*****Reciever Side*****\n";
	cout<<"Recieved Message:";
	for(int i=0;i<dsize+s;i++)
	cout<<trans_data[i];
	
	for(int i=0;i<dsize+s;i++)
	temp[i]=trans_data[i];
	
	division(temp,cgen,dsize,gsize);
	
	cout<<"\nRemainder: ";
	int rem[MAX],flag=0;
	for(int i=0,j=dsize;i<s;i++,j++)
	{
		rem[i]=temp[j];
		if(rem[i]!=0)
		flag=1;
	}
	for(int i=0;i<s;i++)
	cout<<rem[i];
	if(flag==0)
	cout<<"\n\nSince Remainder is 0. Dataword transmitted successfully."<<endl;
	else
	cout<<"\n\nSince Remainder is not 0. Dataword not transmitted  successfully."<<endl;
}
