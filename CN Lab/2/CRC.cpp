#include<iostream>
#include<stdio.h>
#include<vector>


using namespace std;

void getTransmittedWord(vector<int>, vector<int>);
void checkTransmittedWord(vector<int>, vector<int>);

int main() {
    int ch;
    cout<<"1.Get transmitted word ";
    cout<<"\n2.Check if transmitted word is correct ";
    
    char check = 'y';
    do{
        cout<<"\nEnter choice: ";
        cin>>ch;
        switch(ch) {
            case 1: {
                    int len_g, len_d;
                    cout<<"Enter size of dataword: ";
                    cin>>len_d;
                    vector<int> data(len_d);
                    
                    cout<<"Enter data: (separate each bit with space)";
                    for(int i=0; i<len_d; i++) {
                        int d; cin>>d;
                        data[i] = d;
                    }

                    cout<<"Enter size of generator: ";
                    cin>>len_g;
                    vector<int> gen(len_g);
                    cout<<"Enter gen: (separate each bit with space)";
                    for(int i=0; i<len_g; i++) {
                        int d; cin>>d;
                        gen[i] = d;
                    }

                    getTransmittedWord(data,gen);
                    break;
            }
            case 2:{
                    int len_g, len_d;
                    cout<<"Enter size of dataword: ";
                    cin>>len_d;
                    vector<int> data(len_d);
                    
                    cout<<"Enter data: (separate each bit with space)";
                    for(int i=0; i<len_d; i++) {
                        int d; cin>>d;
                        data[i] = d;
                    }

                    cout<<"Enter size of generator: ";
                    cin>>len_g;
                    vector<int> gen(len_g);
                    cout<<"Enter gen: (separate each bit with space)";
                    for(int i=0; i<len_g; i++) {
                        int d; cin>>d;
                        gen[i] = d;
                    }

                    checkTransmittedWord(data, gen);
                    break;
            }
            default: cout<<"Invalid Choice. Please enter a valid choice: ";
        }
        cout<<"Do you want to continue?: (Y/N)";
        cin>>check;
    }while(check=='Y' || check=='y');

    
    

    return 0;
}

void getTransmittedWord(vector<int> data, vector<int> gen) {
    int len_g = gen.size();
    int len_d = data.size();
    vector<int> divident = data;

    //when we resize a vector it automatically appends 0's since that's the default value for int
    data.resize(len_g + len_d - 1);
    len_d += len_g - 1;

    for(int i=0; i<divident.size(); i++) {
        if(data[i] == 1){
            int k = i;
            for(int j= 0; j<len_g; k++,j++) {
                data[k] = data[k] ^ gen[j];
                cout<<data[k]<<" ";
            }cout<<endl;
        }
        else
        {
            while(data[i+1] != 1 && i<divident.size())
                i++;
        }
    } 
    //Since we overwrote the values of data we copy the original divident into the dataword
    //Keeping in mind to not disturb the values of remainder in dataword.
    for(int i=0; i<divident.size(); i++)
        data[i] = divident[i];
    cout<<endl;

    //Display value of remainder.
    cout<<"Remainder is: ";
    for(int i = divident.size(); i<len_d; i++)
        cout<<data[i]<<" "; 

    //Display value of transmitted data.         
    cout<<"\nTransmitted data: ";
    for(int val: data)
        cout<<val<<" "; 
    cout<<endl;      
}   


void checkTransmittedWord(vector<int> data, vector<int> gen) {
    int len_g = gen.size();
    int len_d = data.size();
    vector<int> dataword = data;
    int bitsToCheck = len_d-len_g+1;
    for(int i=0; i<bitsToCheck; i++) {
        if(data[i] == 1){
            int k = i;
            for(int j= 0; j<len_g; k++,j++) {
                data[k] = data[k] ^ gen[j];
                cout<<data[k]<<" ";
            }
            cout<<endl;
        }
        else
        {
            while(data[i+1] != 1 )
                i++;
        }
    } 
    bool flag = true;
    for(int i=bitsToCheck; i<data.size(); i++) {
        if(data[i]!=0) {
            flag = false;
            break;
        }
    }

    if(flag) 
        cout<<"Transmitted word has no error"<<endl;
    else {
        cout<<"Transmitted word has error"<<endl;
        
    }    
}


/* OUTPUT: 

        1.Get transmitted word
        2.Check if transmitted word is correct 
        Enter choice: 1
        Enter size of dataword: 6
        Enter data: (separate each bit with space)1 0 0 1 0 0
        Enter size of generator: 4
        Enter gen: (separate each bit with space)1 1 0 1
        0 1 0 0 
        0 1 0 1 
        0 1 1 1
        0 0 1 1
        0 0 0 1

        Remainder is: 0 0 1
        Transmitted data: 1 0 0 1 0 0 0 0 1 
        Do you want to continue?: (Y/N)y    

        Enter choice: 2
        Enter size of dataword: 9
        Enter data: (separate each bit with space)1 0 0 1 0 0 0 0 1 
        Enter size of generator: 4
        Enter gen: (separate each bit with space)1 1 0 1
        0 1 0 0 
        0 1 0 1
        0 1 1 1
        0 0 1 1
        0 0 0 0
        Transmitted word has no error
        Do you want to continue?: (Y/N)n

*/