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
        let json = await response.json();
        $('.user-feedback').addClass('hidden');
        $('.rate-container span').text(json.rate.toPrecision(2));
        $('#feedbacks-container').before(feedbackHtmlGen(json.login, text, evaluation, localStorage.getItem('id'), localStorage.getItem('avatar')));
    }
}