let lineChart = document.getElementById('weeksChart');
let pieChart = document.getElementById('pieChart');
let lineCharts = new Chart(lineChart, {
    type: 'line',
    data: {
        datasets: [
            {
                label: 'This week',
                data: [12, 222, 3, 5, 243, 34, 34, 234, 2, 3, 4, 234, 45, 34, 34, 234, 2, 55, 54, 43, 23],
                borderColor: [
                    'RGB (255, 191, 0, 0.5)',
                    '#ff9800',
                    '#fff59d'
                ]
            },
            {
                label: 'Prev week',
                data: [45, 34, 342, 34, 234, 278, 34, 234, 452, 55, 54, 34, 34, 234, 2, 43, 23],
                borderColor: [
                    '#388e3c',
                    'RGB (128, 255, 0)',
                    'RGB (0, 255, 191)'
                ]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Your weeks productivity'
        },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});

let pieCharts = new Chart(pieChart, {
    "type": "doughnut",
    "data": {
        "labels":
            [
                "Red",
                "Blue",
                "Yellow"
            ],
        "datasets": [
            {
                "label": "My First Dataset",
                "data": [300, 50, 100],
                "backgroundColor": [
                    "rgb(255, 99, 132)",
                    "rgb(54, 162, 235)",
                    "rgb(255, 205, 86)"
                ]
            }]
    }
});
