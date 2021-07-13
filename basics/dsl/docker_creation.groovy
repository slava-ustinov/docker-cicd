job('NodeJS Docker example') {
    scm {
        git('https://github.com/slava-ustinov/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        dockerBuildAndPublish {
            buildContext('./basics/')
            repositoryName('slavaus/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('Dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
