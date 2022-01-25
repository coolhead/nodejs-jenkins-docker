job('NodeJS Docker example') {
    scm {
        git('git://github.com/coolhead/nodejs-jenkins-docker.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('sraghavendra1512@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps 
    {
            dockerBuildAndPublish 
	    {
            	repositoryName('coolhead/nodejs-jenkins-docker')
            	tag('${GIT_REVISION,length=9}')
            	registryCredentials('dockerhub')
            	forcePull(false)
            	forceTag(false)
            	createFingerprints(false)
            	skipDecorate()

        }
    }
}
