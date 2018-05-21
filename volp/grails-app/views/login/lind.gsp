<span style="font-size:16px;"><span style="font-family:arial,helvetica,sans-serif;">def linkedInConnect() {
        def url = 'https://www.linkedin.com/uas/oauth2/authorization?response_type=code' +
                '&client_id=' + grailsApplication.config.grails.YOUR_API_KEY +
                '&scope=' + grailsApplication.config.grails.YOUR_SCOPE +
                '&state=' + grailsApplication.config.grails.YOUR_STATE +
                '&redirect_uri=' + grailsApplication.config.grails.YOUR_REDIRECT_URI
        log.debug("url " + url)         redirect url : url     }
</span></span>

<span style="font-size:16px;"><span style="font-family:arial,helvetica,sans-serif;">def url = 'https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code' +

                    '&code=' + params.code +

                    '&redirect_uri=' + grailsApplication.config.grails.YOUR_REDIRECT_URI +

                    '&client_id=' + grailsApplication.config.grails.YOUR_API_KEY +

                    '&client_secret=' + grailsApplication.config.grails.YOUR_SECRET_KEY

            // Step 3 - make a call - Pass authorisation code and get access token

            BufferedReader bufferedReaderStep3 = new URL(url).newReader()

            // parse the response

            JSONElement qServiceResponse = JSON.parse(bufferedReaderStep3)

            log.debug qServiceResponse.access_token</span></span>
            <span style="font-size:16px;"><span style="font-family:arial,helvetica,sans-serif;">def getProfileUrl = 'https://api.linkedin.com/v1/people/~:(id,headline,firstName,lastName,picture-url)?format=json&oauth2_access_token=' + qServiceResponse.access_token

                        // Step 4 - Make authenticated request on behalf of the user. Make sure you put "Authorization" header in your HTTP call to LinkedIn's API.

                            BufferedReader bufferedReaderStep4 = new URL(getProfileUrl).newReader()

                            JSONElement qServiceResponse = JSON.parse(bufferedReaderStep4)
            </span></span>