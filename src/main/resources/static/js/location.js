function getLocation() {
    if (navigator.geolocation) {
      navigator = navigator.geolocation.getCurrentPosition(position => {
          this.position = position
      });
    }
  }
  getLocation()