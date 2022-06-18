Rails.application.routes.draw do
  root to: 'home#show'
  get 'sign_in', to: 'sessions#new'
  get 'app_sign_in', to: 'sessions#create'
  delete 'sign_out', to: 'sessions#destroy'
end
