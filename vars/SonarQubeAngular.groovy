def call (Map config)
{
    node
    {
       stage('Analysis quality') 
            {   def scannerHome = tool name: 'sonarscanner';
                    withSonarQubeEnv('SonarQube') {
                    //sh "dotnet restore source/DevOpsProject/DevOpsProject/DevOpsProject.csproj"
                            dir("${config.ProjectName}") {
                                    sh " ls -la ${pwd()}"
                                    //sh "dotnet ${scannerHome}/bin/sonar-scanner  /k:AosoFront /d:sonar.host.url= http://192.168.56.113:9000"
                                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Front -Dsonar.login=squ_6479873c456047cc6b816b8de68e3eb6505eb48d"
                                    //sh "${scannerHome}/bin/sonar-scanner -D /k:Aoso -D /d:sonar.host.url=http://192.168.56.113:9000  -D /d:sonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce "
                                    //sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Aosofront "
                                    
                            }
                }
            }
         stage("QUALITY GATE")
            {
                def qualitygate = waitForQualityGate()

                    if (qualitygate.status != "OK")
                            {

                               input message: "CHECK YOUR QUALITY GATE"
                               waitForQualityGate abortPipeline: true     
                            } 
                    else 
                            {
                             input message: "YOUR APP IS READY TO PACKGING"
                            }
                    
            }
    }
}