import moment from 'moment';

export class TimeGap extends HTMLElement {
  constructor() {
    super();

    const shadow = this.attachShadow({ mode: 'open' });

    this.label = document.createElement('span');
    this.label.innerText = 'vor nicht allzu langer Zeit...';
    shadow.appendChild(this.label);
  }

  connectedCallback() {
    this.updateTimeGap();
  }

  updateTimeGap() {
    const from = this.getAttribute('from');
    const locale = this.getAttribute('locale');
    this.label.innerText = TimeGap.elapsedTime(from, locale);
  }

  static elapsedTime(from, locale) {
    const fromMoment = moment(from).locale(locale);  

    return fromMoment.isSame(new Date(), 'day') ?
      fromMoment.fromNow() :
      fromMoment.calendar();
  }
}