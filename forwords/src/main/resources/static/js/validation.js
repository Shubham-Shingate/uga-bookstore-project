function validate() {
  var f = document.getElementById('registrationForm');

  var hasPaymentError = paymentValidate(f);
  var hasAddressError = addressValidate(f);

  if (!hasPaymentError || !hasAddressError) return false;
  else return true;
}

function validateChangePassword() {
  var f = document.getElementById('changePasswordForm');

  var hasChangePasswordError = changePasswordValidate(f);

  if (!hasChangePasswordError) return false;
  else return true;
}

function validateResetPassword() {
  var f = document.getElementById('resetPasswordForm');

  var hasResetPasswordError = resetPasswordValidate(f);

  if (!hasResetPasswordError) return false;
  else return true;
}

function paymentValidate(form) {
  var error = document.getElementById('paymentError');

  var nameOnCard = form['nameOnCard'].value;
  var cardNumber = form['cardNumber'].value;
  var cardExpiry = form['cardExpiry'].value;
  var cvv = form['cvv'].value;
  var cardType = form['cardType'].value;

  if (
    (nameOnCard.length > 0 &&
      cardNumber.length > 0 &&
      cardExpiry.length > 0 &&
      cvv.length > 0 &&
      cardType.length > 0) ||
    (nameOnCard.length == 0 &&
      cardNumber.length == 0 &&
      cardExpiry.length == 0 &&
      cvv.length == 0 &&
      cardType.length == 0)
  ) {
    return true;
  } else {
    error.innerHTML =
      'Please complete each field for payment or \n Leave all fields for payment as blank';
    return false;
  }
}

function addressValidate(form) {
  var error = document.getElementById('addressError');

  var city = form['city'].value;
  var state = form['state'].value;
  var street = form['street'].value;
  var zipcode = form['zipcode'].value;

  if (
    (city.length > 0 &&
      state.length > 0 &&
      street.length > 0 &&
      zipcode.length > 0) ||
    (city.length == 0 &&
      state.length == 0 &&
      street.length == 0 &&
      zipcode.length == 0)
  ) {
    return true;
  } else {
    error.innerHTML =
      'Please complete each field for address or \n Leave all fields for address as blank\n \t';
    return false;
  }
}

function changePasswordValidate(form) {
  var error = document.getElementById('changePasswordError');

  var oldPassword = form['oldPassword'].value;
  var newPassword = form['newPassword'].value;

  if (
    (oldPassword.length > 0 &&
      newPassword.length > 0)
  ) {
    return true;
  } else {
    error.innerHTML =
      'Please fill out both fields!';
    return false;
  }
}

function resetPasswordValidate(form) {
  var error = document.getElementById('resetPasswordError');

  var newPassword = form['newPassword'].value;
  var confirmNewPassword = form['confirmNewPassword'].value;

  if (
    (newPassword ==
      confirmNewPassword)
  ) {
    return true;
  } else {
    error.innerHTML =
      'Confirm Password do not match!';
    return false;
  }
}


