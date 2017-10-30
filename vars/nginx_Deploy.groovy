#!groovy
/********************************************************************************
***** Description :: This Custom Library is used to Deploy to Remote Server *****
***** Author      :: Mukul Garg                                             *****
***** Date        :: 04/24/2017                                             *****
***** Revision    :: 2.0                                                    *****
********************************************************************************/

import com.sapient.devops.deploy.nginx

def call(body) {
  echo "body : "+body
  def config = [:]
  echo "config obj : "+config

  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  try {
      def remotenginx = new nginx()
    	
        echo "-----------appexDeploy------------"
    	remotenginx.deploy()
  }
  catch (Exception error)
  {
      wrap([$class: 'AnsiColorBuildWrapper']) {
         echo "\u001B[41m[ERROR] ${error}"
         throw error
      }
  }
}