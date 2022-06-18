class HomeController < ApplicationController
  before_action :authenticate_user!

  def show
  end

  private

  def authenticate_user!
    @current_user_name = session[:current_user_name]
    if @current_user_name.blank?
      redirect_to sign_in_path, alert: 'Unauthorized'
    end
  end
end
