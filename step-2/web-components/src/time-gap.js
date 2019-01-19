export class TimeGap extends HTMLElement {
  constructor() {
    super();

    const shadow = this.attachShadow({ mode: 'open' });

    this.label = document.createElement('span');
    this.label.innerText = 'vor nicht allzu langer Zeit...';
    shadow.appendChild(this.label);
  }
}