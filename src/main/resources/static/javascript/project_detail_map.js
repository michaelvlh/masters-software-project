/*
 * @Author: Jipu Li 
 * @Date: 2022-03-26 19:02:53 
 * @Last Modified by: Jipu Li
 * @Last Modified time: 2022-05-09 16:26:20
 */

const countryPositionList = [
  { name: "UK", position: [53.383, -1.466] },
  { name: "China", position: [39.916668, 116.383331] },
  { name: "USA", position: [44.500000, -89.500000] },
  { name: "Japan", position: [35.709026, 139.731992] },
  { name: "New Zealand", position: [-41.28646, 174.776236] },
  { name: "South Africa", position: [-25.747868, 28.229271] }
]



function getPositionByCountryName(project, countryList) {
  var latlng = ""
  countryList.forEach(country => {
    if (country.name == project.positionName) {
      latlng = { lat: country.position[0], lng: country.position[1] }
    } else {
      latlng = { lat: 53.383, lng: -1.466 }
    }
  });
  return latlng
}
// Initialize and add the map 
function initMap() {
  // The location of Uluru
  // The map, centered at Uluru
  var latlng = getPositionByCountryName(project, countryPositionList)

  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 6,
    center: latlng,
  });
  // The marker, positioned at Uluru
  const marker = new google.maps.Marker({
    position: latlng,
    map: map,
  });
}