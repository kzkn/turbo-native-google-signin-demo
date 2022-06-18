require 'net/http'

class GoogleAuth
  class << self
    def verify(id_token)
      result = fetch_token_info(id_token)
      return nil if result.nil?

      result['name']
    end

    def fetch_token_info(token)
      # NOTE: This way is not suitable for use in production code.
      # SEE: https://developers.google.com/identity/sign-in/android/backend-auth#calling-the-tokeninfo-endpoint
      url = URI("https://oauth2.googleapis.com/tokeninfo")
      res = Net::HTTP.post(url, { 'id_token' => token }.to_json, 'Content-Type' => 'application/json')
      if res.code == "200"
        JSON.parse(res.body)
      else
        Rails.logger.info("Failed to verify token, code=#{res.code}, body=#{res.body}")
        nil
      end
    end
  end
end
