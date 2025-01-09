import "./creatorsInfo.css";

export const CreatorsInfo = () => {
  return (
    <div className="info">
        <div className="info__uppe">
          Участники комманды
        </div>
        <div className="info-blocks">
          <div className="info-block">
            <img className="block__img" src="literally_me.jpg" alt="" />
            <p className="block__descr">
              Батаев Бакар
            </p>
          </div>
          <div className="info-block">
            <img className="block__img" src="obuh.jpg" alt="" />
            <p className="block__descr">
              Дмитрий Обухов
            </p>
          </div>
          <div className="info-block">
            <img className="block__img" src="maxim.jpg" alt="" />
            <a className="block__descr" href="https://disk.yandex.ru/d/Bmi0Pmk51zmrFg" target="_blank">
              Максим Осипов
            </a>
          </div>
          <div className="info-block">
            <img className="block__img" src="pni.jpg" alt="" />
            <p className="block__descr">
              Дмитрий Комаров
            </p>
          </div>
          <div className="info-block">
            <img className="block__img" src="wom.jpg" alt="" />
            <p className="block__descr">
              Анна Антонова
            </p>
          </div>
        </div>
    </div>
  )
}