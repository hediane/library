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
                    dotnet restore "Back/scripts/DevOpsProject/DevOpsProject.csproj"
                }
            stage("build") {
                    dotnet build "Back/scripts/DevOpsProject/DevOpsProject.csproj"
                }
            stage("publish") {
                    dotnet publish "Back/scripts/DevOpsProject/DevOpsProject.csproj"
                }
        }
}