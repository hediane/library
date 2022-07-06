def call (Map config)
{
    node
        {
    
                stage('Test unitaire') 
                {  
                    sh 'dotnet test --logger:"trx;logFileName=report.xml" source/DevOpsProject/DevOpsProject.sln'
               
                }
            
        }
        
}