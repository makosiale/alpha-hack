import { FC, useState } from "react";
import "./recommendations.css";

const Arrow = () => {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" width="9" height="16" viewBox="0 0 9 16" fill="none">
      <path d="M0.292893 7.29289C-0.0976311 7.68342 -0.0976311 8.31658 0.292893 8.70711L6.65685 15.0711C7.04738 15.4616 7.68054 15.4616 8.07107 15.0711C8.46159 14.6805 8.46159 14.0474 8.07107 13.6569L2.41421 8L8.07107 2.34315C8.46159 1.95262 8.46159 1.31946 8.07107 0.928932C7.68054 0.538408 7.04738 0.538408 6.65685 0.928932L0.292893 7.29289ZM2 7H1L1 9H2L2 7Z" fill="white"/>
    </svg>
  )
}

const ArrowReversed = () => {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" width="9" height="16" viewBox="0 0 9 16" fill="none">
      <path d="M8.70735 8.70759C9.09787 8.31707 9.09787 7.68391 8.70735 7.29338L2.34339 0.929422C1.95287 0.538898 1.3197 0.538898 0.929177 0.929422C0.538653 1.31995 0.538653 1.95311 0.929177 2.34364L6.58603 8.00049L0.929177 13.6573C0.538653 14.0479 0.538653 14.681 0.929177 15.0716C1.3197 15.4621 1.95287 15.4621 2.34339 15.0716L8.70735 8.70759ZM7.00024 9.00049H8.00024V7.00049H7.00024V9.00049Z" fill="white"/>
    </svg>
  )
}

interface RecommendationsProps {
  finalJson: FinalJsonProps | null;
}

interface FinalJsonProps {
  signatureMethod: string;
  information: string;
}

export const Recommendations: FC<RecommendationsProps> = ({ finalJson }) => {
  const [shown, setShown] = useState<boolean>(false);

  const handleClick = () => {
    if (shown) {
      setShown(false);
    } else {
      setShown(true);
    }
  }

  return (
    <div className={shown ? "recommendations recommendations__active" : "recommendations"}>
      <div className="recommendations-btn" onClick={handleClick}>
        {!shown ? <Arrow /> : <ArrowReversed />}
      </div>
      <div className="recommendations-uppe">
        <h3 className="recommendations__title">
          Рекомендации
        </h3>
        <div className="recommendations-info">
          
          {finalJson === null ? "Пока что рекомендаций нет, сделайте запрос" : (
            <>
              <h3 className="info__title">
                {finalJson.signatureMethod}
              </h3>
              <p className="info__descr">
                {finalJson.information}
              </p>
            </>
          )}
        </div>
      </div>
    </div>
  );
};
