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
             stage("ssh_cnx") {
                    sh 'touch tsthediane' 
                    sh 'scp tsthediane root@87.106.205.95:/srv'
                }
            stage("restore") {
                    sh 'dotnet restore "/var/lib/jenkins/workspace/Back_main/source/DevOpsProject/DevOpsProject.csproj"'
                }
            stage("build") {
                    sh 'dotnet build "/var/lib/jenkins/workspace/Back_main/source/DevOpsProject/DevOpsProject.csproj"'
                }
            stage("publish") {
                   sh 'dotnet publish "/var/lib/jenkins/workspace/Back_main/source/DevOpsProject/DevOpsProject.csproj" '
                }
        }
}