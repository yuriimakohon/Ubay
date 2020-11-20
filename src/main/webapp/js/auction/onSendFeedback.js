async function onSendFeedback() {
    let text = $('#textarea_feedback').val();
    let evaluation = +$('#user-evaluation').text();
    let lotId = localStorage.getItem('lotId');

    // create object for request
    let feedback = {
        text: text,
        evaluation: evaluation,
        lotId: lotId
    }

    // post request
    let response = await fetch("http://localhost:8080/api/feedback", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(feedback)
    });

    if (response.ok) {
        let login = await response.text();
        $('#feedbacks-container').before(feedbackHtmlGen(login, text, evaluation, localStorage.getItem('id'), localStorage.getItem('avatar')));
    }
}