
def call (Map config) {
    pipeline
        {
        agent any 
        stage ('BUILD PROJECT ')
            {
           steps{
            dir("${config.source}")
               {
                sh'dotnet restore'
                sh'dotnet build'
               }
            }
            }
        }
}
