# Turbo Native Google Sign In Demo

A demo application of Google Sign In With Turbo Native.

- web: Rails application which connected from Native App
- android: Android application

## TL;DR

- Launch custom fragment for sign in on visit `/sign_in`
- Get `id_token` from google, and send it to web server with cookies
- Verify `id_token` and get user name from token info
