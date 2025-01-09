# feature_engineer.py

from sklearn.base import BaseEstimator, TransformerMixin

class FeatureEngineer(BaseEstimator, TransformerMixin):
    def fit(self, X, y=None):
        # Этот метод используется для обучения, но в данном случае он не нужен
        return self

    def transform(self, X):
        # Преобразование данных
        X['available_methods_count'] = X[['available_SMS', 'available_PAY_CONTROL', 'available_QS_TOKEN', 'available_QS_MOBILE']].sum(axis=1)
        X['report_methods_count'] = X[['report_SMS', 'report_PAY_CONTROL', 'report_QS_TOKEN', 'report_QS_MOBILE']].sum(axis=1)
        X['common_signature_sum'] = X[['common_SMS_signature', 'common_PAY_CONTROL_signature', 'common_QS_TOKEN_signature', 'common_QS_MOBILE_signature']].sum(axis=1)
        X['special_signature_sum'] = X[['special_SMS_signature', 'special_PAY_CONTROL_signature', 'special_QS_TOKEN_signature', 'special_QS_MOBILE_signature']].sum(axis=1)
        X['balance_opportunity'] = X['context_balanceOpportunity'].map({'YES': 1, 'NO': 0})
        X['urgency_high'] = X['context_urgency'].map({'HIGH': 1, 'LOW': 0})
        return X
    
