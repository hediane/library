def call (Map config)
{
    node
        {
    
                stage('Test unitaire') 
                {  
                 catchError (buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "dotnet test --logger:'trx;logFileName=report.xml' ${config.sln}"
                }
            
        }
        
}
}