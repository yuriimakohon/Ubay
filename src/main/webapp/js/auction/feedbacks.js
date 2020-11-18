function feedbackHtmlGen(login, text, rate, photo) {
  let html = ['            <div class="feedback card">\n' +
  '                <div class="feedback-info-container">\n' +
  '                    <div class="feedback_author">\n' +
  '                        <img src="' + photo + '">\n' +
  '                        <span>' + login + '</span>\n' +
  '                    </div>\n' +
  '                    <div class="feedback-evaluation-container">\n'];

  for (let i = 0; i < rate; i++) {
    html += '                        <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">\n';
  }
  for (; rate < 5; rate++) {
    html += '                        <img class="evaluation_start" src="/resources/start.svg" alt="star">\n';
  }

  html += ['                    </div>\n' +
  '                </div>\n' +
  '                <p class="feedback-text">' + text + '<p>\n' +
  '            </div>'];
  return html;
}


async function onFeedbacks(id) {
  $('.feedbacks').removeClass('hidden');
  alert("REQUEST: Feedbacks for ID: " + id);

  //======================== DELETE
  $('.feedbacks-container').append(feedbackHtmlGen('user1', 'Bad', 1,'/resources/test.png'));
  $('.feedbacks-container').append(feedbackHtmlGen('user2', 'Cosi-cosi', 3, '/resources/test2.jpeg'));
  $('.feedbacks-container').append(feedbackHtmlGen('user3', 'АВТАР - ЛУЧШИЙ!!', 5, '/resources/test2.jpeg'));
  //======================== DELETE
}













// <div class="feedback card">
//   <div class="feedback-info-container">
//     <div class="feedback_author">
//       <img src="/resources/test2.jpeg">
//         <span>user_login</span>
//     </div>
//     <div class="feedback-evaluation-container">
//       <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//         <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//           <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//             <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//               <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//     </div>
//   </div>
//   <p class="feedback-text">
//     Lorem ipsum dolor sit amet, consectetur adipiscing elit,
//     sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
//     Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
//     nisi ut aliquip ex ea commodo consequat.
//   </p>
// </div>