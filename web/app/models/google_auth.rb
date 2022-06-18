class GoogleAuth
  class << self
    def verify(id_token)
      result = fetch_token_info(id_token)
      return nil if result.nil?

      result['name']
    end

    def fetch_token_info(token)
      validator = GoogleIDToken::Validator.new
      validator.check(token, '<YOUR OAUTH CLIENT ID HERE>')
    rescue GoogleIDToken::ValidationError => e
      Rails.logger.info("Failed to verify token, #{e}")
      nil
    end
  end
end
