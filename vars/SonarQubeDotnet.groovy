def call (Map config)
{
    node
    {
         
        stage('SONAR BUILD') 
                {   sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} build"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} up -d --scale sonarqube=3"
                }
        
        /*stage('Sonar Quality Analysis')
               {
                    def scannerHome = tool name:'SonarScanner for MSBuild'
                    withSonarQubeEnv('SonarQube')
                    {
                        dir("${config.ProjectName}") {
                        sh " ls -la ${pwd()}"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:aosohediane /d:sonar.login=23b5d4c1c1f76c539f1d0019945228a4003d6a51 "
                        sh "dotnet build"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end /d:sonar.login=23b5d4c1c1f76c539f1d0019945228a4003d6a51"
                        //a650a854dfc5fdfd835f432b6cbf52f369f6a2b1"
                        }
                    }
                }*/
    }
}