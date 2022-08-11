class SessionsController < ApplicationController
  def new
  end

  def create
    name = GoogleAuth.verify(cookies.delete(:id_token))
    if name.present?
      session[:current_user_name] = name
      redirect_to root_path, notice: 'Signed in'
    else
      redirect_to sign_in_path
    end
  end

  def destroy
    session.delete(:current_user_name)
    redirect_to sign_in_path, notice: 'Signed out'
  end
end
