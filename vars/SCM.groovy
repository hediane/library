def call (Map config)
{
    node
        { 
            stage("GitSCM") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
               
            ])
                }
            stage("restore") {
                    sh 'dotnet restore "Back/scripts/DevOpsProject/DevOpsProject.csproj"'
                }
            stage("build") {
                    sh 'dotnet build "Back/scripts/DevOpsProject/DevOpsProject.csproj"'
                }
            stage("publish") {
                   sh 'dotnet publish "Back/scripts/DevOpsProject/DevOpsProject.csproj" '
                }
        }
}