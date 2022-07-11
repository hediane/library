
def call (Map config) {
    pipeline
        {
        agent any 
        stages
        {
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
}
