def call (Map config)
{
    node
    {
         
        /*stage('SONAR BUILD') 
                {   sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} build"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} up -d"
                }*/
        
        stage('Sonar Quality Analysis')
               {
                    def scannerHome = tool name:'SonarScanner for MSBuild'
                    withSonarQubeEnv('SonarQube')
                    {
                        dir("${config.ProjectName}") {
                        sh " ls -la ${pwd()}"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:Back /d:sonar.login=aosora "
                        sh "dotnet build"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end /d:sonar.login=aosora"
                        //a650a854dfc5fdfd835f432b6cbf52f369f6a2b1"
                        }
                    }
                }
    }
}