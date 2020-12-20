/**
 * Function to get the users current locations and append it to a public variable on the document object.
 */
function getLocation() {
    if (navigator.geolocation) {
      navigator = navigator.geolocation.getCurrentPosition(position => {
          this.position = position
      });
    }
  }
  getLocation()